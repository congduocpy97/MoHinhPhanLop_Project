package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_PhanBo;
import dto.PhanBo;

@RestController
public class API_PhanBo {

	@PostMapping(value = "/phanbo_search")
	@ResponseBody
	public PhanBo[] search(@RequestBody PhanBo phanbo) {
		ArrayList<PhanBo> arr = DAO_PhanBo.search(phanbo);
		return arr.toArray(new PhanBo[arr.size()]);
	}
	
	@GetMapping(value = "/phanbo_getall")
	public PhanBo[] getAll() {
		ArrayList<PhanBo> arr = DAO_PhanBo.search(new PhanBo(-1, -1, null));
		return arr.toArray(new PhanBo[arr.size()]);
	}
	
	@PostMapping(value = "/phanbo_find")
	@ResponseBody
	public PhanBo find(@RequestBody String[] code) {
		return DAO_PhanBo.findById(Integer.parseInt(code[0]), Integer.parseInt(code[1]));
	}
	
	@PostMapping(value = "/phanbo_create")
	@ResponseBody
	public int create(@RequestBody PhanBo phanbo) {
		return DAO_PhanBo.create(phanbo);
	}
	
	@PostMapping(value = "/phanbo_update")
	@ResponseBody
	public int update(@RequestBody PhanBo[] phanbo) {
		return DAO_PhanBo.update(phanbo[0], phanbo[1]);
	}
	
	@PostMapping(value = "/phanbo_delete")
	@ResponseBody
	public int delete(@RequestBody PhanBo phanbo) {
		return DAO_PhanBo.delete(phanbo);
	}
}