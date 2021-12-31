package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_ChiTietDoan;
import dto.ChiTietDoan;

@RestController
public class API_ChiTietDoan {

	@PostMapping(value = "/chitietdoan_search")
	@ResponseBody
	public ChiTietDoan[] search(@RequestBody ChiTietDoan chitietdoan) {
		ArrayList<ChiTietDoan> arr = DAO_ChiTietDoan.search(chitietdoan);
		return arr.toArray(new ChiTietDoan[arr.size()]);
	}
	
	@GetMapping(value = "/chitietdoan_getall")
	public ChiTietDoan[] getAll() {
		ArrayList<ChiTietDoan> arr = DAO_ChiTietDoan.search(new ChiTietDoan(-1, -1));
		return arr.toArray(new ChiTietDoan[arr.size()]);
	}
	
	@PostMapping(value = "/chitietdoan_create")
	@ResponseBody
	public int create(@RequestBody ChiTietDoan chitietdoan) {
		return DAO_ChiTietDoan.create(chitietdoan);
	}
	
	@PostMapping(value = "/chitietdoan_update")
	@ResponseBody
	public int update(@RequestBody ChiTietDoan[] chitietdoan) {
		return DAO_ChiTietDoan.update(chitietdoan[0], chitietdoan[1]);
	}
	
	@PostMapping(value = "/chitietdoan_delete")
	@ResponseBody
	public int delete(@RequestBody ChiTietDoan chitietdoan) {
		return DAO_ChiTietDoan.delete(chitietdoan);
	}
}