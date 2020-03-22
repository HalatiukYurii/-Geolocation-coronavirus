package com.yurii.springbootmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {

//	@RequestMapping(method = RequestMethod.GET)
//	public String getMap(Model model, @RequestParam String x, @RequestParam String y) {
//		model.addAttribute("x", x);
//		model.addAttribute("y", y);
//		return "map";
//	}

	private Covid19Confirmed covid19Confirmed;
	

	public MapController(Covid19Confirmed covid19Confirmed) {
	this.covid19Confirmed = covid19Confirmed;
}


	@RequestMapping(method = RequestMethod.GET)
	public String getMap(Model model) throws IOException, InterruptedException {

		model.addAttribute("points", covid19Confirmed.getCovidData());
		return "map";
	}

}
