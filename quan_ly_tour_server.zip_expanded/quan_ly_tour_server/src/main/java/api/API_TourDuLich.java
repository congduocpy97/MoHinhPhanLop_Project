package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_TourDuLich;
import dto.TourDuLich;

@RestController
public class API_TourDuLich {

	@PostMapping(value = "/tourdulich_search")
	@ResponseBody
	public TourDuLich[] search(@RequestBody TourDuLich tourdulich) {
		ArrayList<TourDuLich> arr = DAO_TourDuLich.search(tourdulich);
		return arr.toArray(new TourDuLich[arr.size()]);
	}
	
	@GetMapping(value = "/tourdulich_getall")
	public TourDuLich[] getAll() {
		ArrayList<TourDuLich> arr = DAO_TourDuLich.search(new TourDuLich(-1, null, null, -1, null));
		return arr.toArray(new TourDuLich[arr.size()]);
	}
	
	@PostMapping(value = "/tourdulich_find")
	@ResponseBody
	public TourDuLich find(@RequestBody String[] code) {
		return DAO_TourDuLich.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/tourdulich_create")
	@ResponseBody
	public int create(@RequestBody TourDuLich tourdulich) {
		return DAO_TourDuLich.create(tourdulich);
	}
	
	@PostMapping(value = "/tourdulich_update")
	@ResponseBody
	public int update(@RequestBody TourDuLich[] tourdulich) {
		return DAO_TourDuLich.update(tourdulich[0], tourdulich[1]);
	}
	
	@PostMapping(value = "/tourdulich_delete")
	@ResponseBody
	public int delete(@RequestBody TourDuLich tourdulich) {
		return DAO_TourDuLich.delete(tourdulich);
	}
	
	@PostMapping(value = "/tourdulich_getlastindex")
	@ResponseBody
	public int getLastIndex() {
		return DAO_TourDuLich.getLastInsertID();
	}
}