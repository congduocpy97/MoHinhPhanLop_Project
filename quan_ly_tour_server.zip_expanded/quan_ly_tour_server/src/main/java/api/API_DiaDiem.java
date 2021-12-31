package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_DiaDiem;
import dto.DiaDiem;

@RestController
public class API_DiaDiem {

	@PostMapping(value = "/diadiem_search")
	@ResponseBody
	public DiaDiem[] search(@RequestBody DiaDiem diadiem) {
		ArrayList<DiaDiem> arr = DAO_DiaDiem.search(diadiem);
		return arr.toArray(new DiaDiem[arr.size()]);
	}
	
	@GetMapping(value = "/diadiem_getall")
	public DiaDiem[] getAll() {
		ArrayList<DiaDiem> arr = DAO_DiaDiem.search(new DiaDiem(-1, null, null));
		return arr.toArray(new DiaDiem[arr.size()]);
	}
	
	@PostMapping(value = "/diadiem_find")
	@ResponseBody
	public DiaDiem find(@RequestBody String[] code) {
		return DAO_DiaDiem.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/diadiem_create")
	@ResponseBody
	public int create(@RequestBody DiaDiem diadiem) {
		return DAO_DiaDiem.create(diadiem);
	}
	
	@PostMapping(value = "/diadiem_update")
	@ResponseBody
	public int update(@RequestBody DiaDiem[] diadiem) {
		return DAO_DiaDiem.update(diadiem[0], diadiem[1]);
	}
	
	@PostMapping(value = "/diadiem_delete")
	@ResponseBody
	public int delete(@RequestBody DiaDiem diadiem) {
		return DAO_DiaDiem.delete(diadiem);
	}
}