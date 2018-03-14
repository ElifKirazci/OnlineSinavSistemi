package com.calisma.odevsinav;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Property.SoruPro;

@Controller
public class HomeController {
	
	DB db = new DB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/sinav", method = RequestMethod.GET)
	public String duzenleAcil(Model model) {
		List<SoruPro> sol = soruDoldur();
		if(sol.size() > 0) {
			model.addAttribute("sol", sol);
		}
		return "sinavduzenle";
	}
	
	public List<SoruPro>soruDoldur() {
		List<SoruPro> sol = new ArrayList<SoruPro>();
		try {
			String q = "{call sorugetirme()}";
			ResultSet rs = db.baglan().executeQuery(q);
			int i=1;
			while(rs.next()) {
				
				SoruPro sp = new SoruPro();
				sp.setSorunumarasi(i);
				sp.setSoruid(rs.getString(1));
				sp.setSoru(rs.getString(2));
				sol.add(sp);
				i++;
			}
			
		} catch (Exception e) {
			System.err.println("Soru Getirme Hatasý" + e);
		}
		return sol;
	}
}
