package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_ChiPhi;
import dto.ChiPhi;

@RestController
public class API_ChiPhi {

	@PostMapping(value = "/chiphi_search")
	@ResponseBody
	public ChiPhi[] search(@RequestBody ChiPhi chiphi) {
		ArrayList<ChiPhi> arr = DAO_ChiPhi.search(chiphi);
		return arr.toArray(new ChiPhi[arr.size()]);
	}
	
	@GetMapping(value = "/chiphi_getall")
	public ChiPhi[] getAll() {
		ArrayList<ChiPhi> arr = DAO_ChiPhi.search(new ChiPhi(-1, -1, -1, -1));
		return arr.toArray(new ChiPhi[arr.size()]);
	}
	
	@PostMapping(value = "/chiphi_find")
	@ResponseBody
	public ChiPhi find(@RequestBody String[] code) {
		return DAO_ChiPhi.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/chiphi_create")
	@ResponseBody
	public int create(@RequestBody ChiPhi chiphi) {
		return DAO_ChiPhi.create(chiphi);
	}
	
	@PostMapping(value = "/chiphi_update")
	@ResponseBody
	public int update(@RequestBody ChiPhi[] chiphi) {
		return DAO_ChiPhi.update(chiphi[0], chiphi[1]);
	}
	
	@PostMapping(value = "/chiphi_delete")
	@ResponseBody
	public int delete(@RequestBody ChiPhi chiphi) {
		return DAO_ChiPhi.delete(chiphi);
	}
}