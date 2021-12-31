package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_LoaiChiPhi;
import dto.LoaiChiPhi;

@RestController
public class API_LoaiChiPhi {

	@PostMapping(value = "/loaichiphi_search")
	@ResponseBody
	public LoaiChiPhi[] search(@RequestBody LoaiChiPhi loaichiphi) {
		ArrayList<LoaiChiPhi> arr = DAO_LoaiChiPhi.search(loaichiphi);
		return arr.toArray(new LoaiChiPhi[arr.size()]);
	}
	
	@GetMapping(value = "/loaichiphi_getall")
	public LoaiChiPhi[] getAll() {
		ArrayList<LoaiChiPhi> arr = DAO_LoaiChiPhi.search(new LoaiChiPhi(-1, null, null));
		return arr.toArray(new LoaiChiPhi[arr.size()]);
	}
	
	@PostMapping(value = "/loaichiphi_find")
	@ResponseBody
	public LoaiChiPhi find(@RequestBody String[] code) {
		return DAO_LoaiChiPhi.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/loaichiphi_create")
	@ResponseBody
	public int create(@RequestBody LoaiChiPhi loaichiphi) {
		return DAO_LoaiChiPhi.create(loaichiphi);
	}
	
	@PostMapping(value = "/loaichiphi_update")
	@ResponseBody
	public int update(@RequestBody LoaiChiPhi[] loaichiphi) {
		return DAO_LoaiChiPhi.update(loaichiphi[0], loaichiphi[1]);
	}
	
	@PostMapping(value = "/loaichiphi_delete")
	@ResponseBody
	public int delete(@RequestBody LoaiChiPhi loaichiphi) {
		return DAO_LoaiChiPhi.delete(loaichiphi);
	}
}