package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_DoanDuLich;
import dto.DoanDuLich;

@RestController
public class API_DoanDuLich {

	@PostMapping(value = "/doandulich_search")
	@ResponseBody
	public DoanDuLich[] search(@RequestBody DoanDuLich doandulich) {
		ArrayList<DoanDuLich> arr = DAO_DoanDuLich.search(doandulich);
		return arr.toArray(new DoanDuLich[arr.size()]);
	}
	
	@GetMapping(value = "/doandulich_getall")
	public DoanDuLich[] getAll() {
		ArrayList<DoanDuLich> arr = DAO_DoanDuLich.search(new DoanDuLich(-1, -1, null, null, -1, null));
		return arr.toArray(new DoanDuLich[arr.size()]);
	}
	
	@PostMapping(value = "/doandulich_find")
	@ResponseBody
	public DoanDuLich find(@RequestBody String[] code) {
		return DAO_DoanDuLich.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/doandulich_create")
	@ResponseBody
	public int create(@RequestBody DoanDuLich doandulich) {
		return DAO_DoanDuLich.create(doandulich);
	}
	
	@PostMapping(value = "/doandulich_update")
	@ResponseBody
	public int update(@RequestBody DoanDuLich[] doandulich) {
		return DAO_DoanDuLich.update(doandulich[0], doandulich[1]);
	}
	
	@PostMapping(value = "/doandulich_delete")
	@ResponseBody
	public int delete(@RequestBody DoanDuLich doandulich) {
		return DAO_DoanDuLich.delete(doandulich);
	}
	
	@PostMapping(value = "/doandulich_getlastindex")
	public int getLastIndex() {
		return DAO_DoanDuLich.getLastInsertID();
	}
}