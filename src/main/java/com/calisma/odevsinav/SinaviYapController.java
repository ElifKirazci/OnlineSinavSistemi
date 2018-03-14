package com.calisma.odevsinav;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Property.CevapPro;
import Property.SecenekPro;
import Property.SoruPro;

@Controller
public class SinaviYapController {

	DB db = new DB();

	@RequestMapping(value = "/sinavyap", method = RequestMethod.GET)
	public String SinaviYap(Model model) {
		model.addAttribute("sinavdurumu", "sinav basla!");
		return "sinavyap";
	}

	int i;
	int soruSayisi=0;
	@RequestMapping(value = "/sinavsorulari", method = RequestMethod.GET)
	public String SinavSorulari(Model model) {
		i = 0;
		List<SoruPro> sinavSoruls = sinavSorulariGetir();
		soruSayisi=sinavSoruls.size();
		model.addAttribute("i", (i + 1));
		model.addAttribute("soru", sinavSoruls.get(i));
		model.addAttribute("baslik","Sorular");
		List<SecenekPro> soruSecenekls = new SinaviDuzenleController().secenekGetir(sinavSoruls.get(i).getSoruid());
		model.addAttribute("secenekLs", soruSecenekls);

		return "sinavyap";
	}

	@RequestMapping(value = "/sinavsorulari", method = RequestMethod.POST)
	public String SinavSorularii(Model model, @RequestParam(defaultValue = "0") String secilen) {
		List<SoruPro> sinavSoruls = sinavSorulariGetir();
		String q = "{call sonucekleme('" + secilen + "','" + sinavSoruls.get(i).getSoruid() + "')}";
		try {
			int yaz = db.baglan().executeUpdate(q);
			if (yaz > 0) {
				System.out.println("Basarili");
			} else {
				System.out.println("Basarisiz");
			}
		} catch (Exception e) {
			System.err.println("Sonuc Ekleme Hatasi : " + e);
		}

		System.out.println("secilen:" + secilen);

		i++;

		if (i < sinavSoruls.size()) {

			model.addAttribute("i", (i + 1));
			model.addAttribute("soru", sinavSoruls.get(i));
			model.addAttribute("baslik","Sorular");
			List<SecenekPro> soruSecenekls = new SinaviDuzenleController().secenekGetir(sinavSoruls.get(i).getSoruid());
			model.addAttribute("secenekLs", soruSecenekls);
		} else {
			model.addAttribute("sinavbitti", "sinavbitti");
			model.addAttribute("baslik","Sonuçlar");
		}
		return "sinavyap";
	}

	@RequestMapping(value = "/sonuc", method = RequestMethod.GET)
	public String sonucGetir(Model model) {
		List<CevapPro> cevaplist = sonucDoldur();
		model.addAttribute("cevaplist", cevaplist);

		return "sonuc";
	}

	public List<CevapPro> sonucDoldur() {
		List<CevapPro> cevaplist = new ArrayList<CevapPro>();
		String q = "{call sonucgetir('"+soruSayisi+"')}";
		try {
int j =0;
			ResultSet rs = db.baglan().executeQuery(q);
			while (rs.next()) {
				j++;
				CevapPro cep = new CevapPro();
				cep.setSoru(rs.getString(1));
				cep.setDurum(rs.getString(2));
				cep.setSoruno(j+"");
				System.out.println("resulset ýn içi soru " + rs.getString(1));
				System.out.println("j degeri;"+cep.getSoruno());
				cevaplist.add(cep);
			}

		} catch (Exception e) {
			System.err.println("Sonuc Gösterme Hatasi " + e);
		}
		return cevaplist;
	}

	public List<SoruPro> sinavSorulariGetir() {
		List<SoruPro> sinavSoruls = new ArrayList<SoruPro>();

		try {
			String q = "{call secOlanSorular()}";
			ResultSet rs = db.baglan().executeQuery(q);
			while (rs.next()) {
				SoruPro sep = new SoruPro();
				sep.setSoruid(rs.getString(1));
				sep.setSoru(rs.getString(2));
				sinavSoruls.add(sep);
			}
		} catch (Exception e) {
			System.err.println("secOlanSorular Hatasi" + e);
		}
		return sinavSoruls;
	}

}
