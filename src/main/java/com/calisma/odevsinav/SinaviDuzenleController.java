package com.calisma.odevsinav;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Property.SecenekPro;
import Property.SoruPro;

@Controller
public class SinaviDuzenleController {
	DB db = new DB();

	
	@RequestMapping(value ="/sinavduzenle" , method=RequestMethod.POST)
	public String SinavDuzenle(@RequestParam String soru, Model model) {
		return "sinavduzenle";
	}
	
	@RequestMapping(value="/soruekleme", method=RequestMethod.POST)
	public String soruEkleme(@RequestParam String soru, Model model) {
		if(soru.trim().equals("")) {
			System.out.println("soru" + soru);
			model.addAttribute("hata", "Lütfen Soru Yaziniz");
		}else {
			try {
				int yaz = db.baglan().executeUpdate("{call soruekleme('"+soru+"')}");
			if(yaz>0) {
				System.out.println("Basarili");
			}else {
				System.out.println("Basarisiz");
			}
			} catch (Exception e) {
				System.err.println("Soru Ekleme Hatasi : " + e);
			}
		}
		return "redirect:/sinav";
	}
	
	@RequestMapping(value="/sorusil/{soruid}", method=RequestMethod.GET)
	public String SoruSil(@PathVariable String soruid,Model model) {
		try {
			String q = "{call sorusil('"+soruid+"')}";
			int yaz= db.baglan().executeUpdate(q);
			if(yaz>0) {
				System.out.println("Basarili");
			}else {
				System.out.println("Basarisiz");
			}
		} catch (Exception e) {
			System.err.println("Soru Silme hatasi"+e);
		}
		
		List<SoruPro> sol = new HomeController().soruDoldur();
		if(sol.size() > 0) {
			model.addAttribute("sol", sol);
		}
		return "sinavduzenle";
	}
	
	@RequestMapping(value="/secenekekle/{soruID}", method=RequestMethod.GET)
	public String soruGetirme(@PathVariable String soruID, Model model) {
		String sell = soruGetir(soruID) ;
		model.addAttribute("sell",sell);
		model.addAttribute("sid",soruID);
		List<SecenekPro> seclist = secenekGetir(soruID) ;
		model.addAttribute("seclist",seclist);
		
		return "secenekekle"; 
	}
	
	public List<SecenekPro> secenekDoldur(@RequestParam String soruid) { 
		List<SecenekPro> sel =new  ArrayList<SecenekPro>();
		try {
			String q = "{call secenekgetirme('"+soruid+"')}";
			ResultSet rs = db.baglan().executeQuery(q);
			while(rs.next()) {	
				SecenekPro sep = new SecenekPro();
				sep.setSecenekid(rs.getString(1));
				sep.setSecenek(rs.getString(2));
				sep.setSoruid(rs.getString(3));
				sep.setDurum(rs.getString(4));
				sep.setSoru(rs.getString(6));
				sel.add(sep);
				System.out.println(sel);
			}
		} catch (Exception e) {
			System.err.println("Secenek Getirme Hatasi" + e);
		}
		return sel;
	}
	
	@RequestMapping(value="/secekle/{soruID}", method= RequestMethod.POST)
	public String cevapEkle(@RequestParam String secenek,@RequestParam String durum,@PathVariable String soruID,Model model) {
		if(secenek.trim().equals("")) {
			model.addAttribute("Hata", "Lütfen Secenek Yaziniz");
		}else {
			String q ="{call secenekekleme ('"+secenek+"','"+soruID+"','"+durum+"')}";
			try {
				int yaz=db.baglan().executeUpdate(q);
				if(yaz>0) {
					System.out.println("Basarili");
				}else {
					System.out.println("Basarisiz");
				}
			} catch (Exception e) {
				System.err.println("Secenek Ekleme Hatasi :"+e);
			}
		}
		return "redirect:/secenekekle/"+soruID;
	}
	public String soruGetir(String id) {
		String sell="";
		try {
		
		String q="{call secenegesorugetirme('"+id+"')}"; 
			ResultSet rs=db.baglan().executeQuery(q);
			if(rs.next()) {
				sell=rs.getString(1);
			}
		} catch (Exception e) {
		   System.err.println("Secenek Ekleye Soru Getirme Hatasi :"+e);
		}
		return sell;
	}
	
	public List<SecenekPro> secenekGetir(String id) {
		List<SecenekPro> seclist = new ArrayList<SecenekPro>();
		try {
		
		String q="{call secenekgetir('"+id+"')}"; 
			ResultSet rs=db.baglan().executeQuery(q);
			while(rs.next()) {
				SecenekPro sep= new SecenekPro();
				sep.setSecenekid(rs.getString(1));
				sep.setSecenek(rs.getString(2));
				sep.setSoruid(rs.getString(3));
				sep.setDurum(rs.getString(4));
				seclist.add(sep); 
			}
			
		} catch (Exception e) {
		   System.err.println("Secenek Getirme Hatasi :"+e);
		}
		return seclist;
	}
	
	
	@RequestMapping(value="/seceneksil/{soruid}/{secenekid}", method=RequestMethod.GET)
	public String SecenekSil(@PathVariable String secenekid,@PathVariable String soruid,Model model) {
		try {
			String q = "{call seceneksil('"+secenekid+"','"+soruid+"')}";
			int yaz= db.baglan().executeUpdate(q);
			if(yaz>0) {
				System.out.println("Basarili");
			}else {
				System.out.println("Basarisiz");
			}
		} catch (Exception e) {
			System.err.println("Secenek Silme hatasi"+e);
		}
		List<SecenekPro> seclist =secenekGetir(secenekid);
		if(seclist.size() > 0) {
			model.addAttribute("seclist", seclist);
		}
		return "redirect:/secenekekle/"+soruid;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
