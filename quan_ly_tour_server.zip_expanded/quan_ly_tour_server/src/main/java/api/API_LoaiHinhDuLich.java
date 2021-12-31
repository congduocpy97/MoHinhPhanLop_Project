package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_LoaiHinhDuLich;
import dto.LoaiHinhDuLich;

@RestController
public class API_LoaiHinhDuLich {

	@PostMapping(value = "/loaihinhdulich_search")
	@ResponseBody
	public LoaiHinhDuLich[] search(@RequestBody LoaiHinhDuLich loaihinhdulich) {
		ArrayList<LoaiHinhDuLich> arr = DAO_LoaiHinhDuLich.search(loaihinhdulich);
		return arr.toArray(new LoaiHinhDuLich[arr.size()]);
	}
	
	@GetMapping(value = "/loaihinhdulich_getall")
	public LoaiHinhDuLich[] getAll() {
		ArrayList<LoaiHinhDuLich> arr = DAO_LoaiHinhDuLich.search(new LoaiHinhDuLich(-1, null, null));
		return arr.toArray(new LoaiHinhDuLich[arr.size()]);
	}
	
	@PostMapping(value = "/loaihinhdulich_find")
	@ResponseBody
	public LoaiHinhDuLich find(@RequestBody String[] code) {
		return DAO_LoaiHinhDuLich.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/loaihinhdulich_create")
	@ResponseBody
	public int create(@RequestBody LoaiHinhDuLich loaihinhdulich) {
		return DAO_LoaiHinhDuLich.create(loaihinhdulich);
	}
	
	@PostMapping(value = "/loaihinhdulich_update")
	@ResponseBody
	public int update(@RequestBody LoaiHinhDuLich[] loaihinhdulich) {
		return DAO_LoaiHinhDuLich.update(loaihinhdulich[0], loaihinhdulich[1]);
	}
	
	@PostMapping(value = "/loaihinhdulich_delete")
	@ResponseBody
	public int delete(@RequestBody LoaiHinhDuLich loaihinhdulich) {
		return DAO_LoaiHinhDuLich.delete(loaihinhdulich);
	}
}