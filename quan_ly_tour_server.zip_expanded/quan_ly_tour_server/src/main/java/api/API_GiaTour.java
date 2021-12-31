package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_GiaTour;
import dto.GiaTour;

@RestController
public class API_GiaTour {

	@PostMapping(value = "/giatour_search")
	@ResponseBody
	public GiaTour[] search(@RequestBody GiaTour giatour) {
		ArrayList<GiaTour> arr = DAO_GiaTour.search(giatour);
		return arr.toArray(new GiaTour[arr.size()]);
	}
	
	@GetMapping(value = "/giatour_getall")
	public GiaTour[] getAll() {
		ArrayList<GiaTour> arr = DAO_GiaTour.search(new GiaTour(-1, -1, -1, null, null, null));
		return arr.toArray(new GiaTour[arr.size()]);
	}
	
	@PostMapping(value = "/giatour_find")
	@ResponseBody
	public GiaTour find(@RequestBody String[] code) {
		return DAO_GiaTour.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/giatour_create")
	@ResponseBody
	public int create(@RequestBody GiaTour giatour) {
		return DAO_GiaTour.create(giatour);
	}
	
	@PostMapping(value = "/giatour_update")
	@ResponseBody
	public int update(@RequestBody GiaTour[] giatour) {
		return DAO_GiaTour.update(giatour[0], giatour[1]);
	}
	
	@PostMapping(value = "/giatour_delete")
	@ResponseBody
	public int delete(@RequestBody GiaTour giatour) {
		return DAO_GiaTour.delete(giatour);
	}
}