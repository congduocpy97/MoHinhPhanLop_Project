package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_NoiDungTour;
import dto.NoiDungTour;

@RestController
public class API_NoiDungTour {

	@PostMapping(value = "/noidungtour_search")
	@ResponseBody
	public NoiDungTour[] search(@RequestBody NoiDungTour noidungtour) {
		ArrayList<NoiDungTour> arr = DAO_NoiDungTour.search(noidungtour);
		return arr.toArray(new NoiDungTour[arr.size()]);
	}
	
	@GetMapping(value = "/noidungtour_getall")
	public NoiDungTour[] getAll() {
		ArrayList<NoiDungTour> arr = DAO_NoiDungTour.search(new NoiDungTour(-1, null, null, null));
		return arr.toArray(new NoiDungTour[arr.size()]);
	}
	
	@PostMapping(value = "/noidungtour_find")
	@ResponseBody
	public NoiDungTour find(@RequestBody String[] code) {
		return DAO_NoiDungTour.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/noidungtour_create")
	@ResponseBody
	public int create(@RequestBody NoiDungTour noidungtour) {
		return DAO_NoiDungTour.create(noidungtour);
	}
	
	@PostMapping(value = "/noidungtour_update")
	@ResponseBody
	public int update(@RequestBody NoiDungTour[] noidungtour) {
		return DAO_NoiDungTour.update(noidungtour[0], noidungtour[1]);
	}
	
	@PostMapping(value = "/noidungtour_delete")
	@ResponseBody
	public int delete(@RequestBody NoiDungTour noidungtour) {
		return DAO_NoiDungTour.delete(noidungtour);
	}
}