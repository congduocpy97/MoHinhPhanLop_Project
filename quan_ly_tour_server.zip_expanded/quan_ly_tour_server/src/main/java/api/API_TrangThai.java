package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_TrangThai;
import dto.TrangThai;

@RestController
public class API_TrangThai {

	@PostMapping(value = "/trangthai_search")
	@ResponseBody
	public TrangThai[] search(@RequestBody TrangThai trangthai) {
		ArrayList<TrangThai> arr = DAO_TrangThai.search(trangthai);
		return arr.toArray(new TrangThai[arr.size()]);
	}
	
	@GetMapping(value = "/trangthai_getall")
	public TrangThai[] getAll() {
		ArrayList<TrangThai> arr = DAO_TrangThai.search(new TrangThai(null,null));
		return arr.toArray(new TrangThai[arr.size()]);
	}
	
	@PostMapping(value = "/trangthai_find")
	@ResponseBody
	public TrangThai find(@RequestBody String[] code) {
		return DAO_TrangThai.findById(code[0]);
	}
	
	@PostMapping(value = "/trangthai_create")
	@ResponseBody
	public int create(@RequestBody TrangThai trangthai) {
		return DAO_TrangThai.create(trangthai);
	}
	
	@PostMapping(value = "/trangthai_update")
	@ResponseBody
	public int update(@RequestBody TrangThai[] trangthai) {
		return DAO_TrangThai.update(trangthai[0], trangthai[1]);
	}
	
	@PostMapping(value = "/trangthai_delete")
	@ResponseBody
	public int delete(@RequestBody TrangThai trangthai) {
		return DAO_TrangThai.delete(trangthai);
	}
	
}