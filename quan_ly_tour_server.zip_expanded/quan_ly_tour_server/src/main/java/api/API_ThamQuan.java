package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_ThamQuan;
import dto.ThamQuan;

@RestController
public class API_ThamQuan {

	@PostMapping(value = "/thamquan_search")
	@ResponseBody
	public ThamQuan[] search(@RequestBody ThamQuan thamquan) {
		ArrayList<ThamQuan> arr = DAO_ThamQuan.search(thamquan);
		return arr.toArray(new ThamQuan[arr.size()]);
	}
	
	@GetMapping(value = "/thamquan_getall")
	public ThamQuan[] getAll() {
		ArrayList<ThamQuan> arr = DAO_ThamQuan.search(new ThamQuan(-1, -1, -1, null));
		return arr.toArray(new ThamQuan[arr.size()]);
	}
	
	@PostMapping(value = "/thamquan_find")
	@ResponseBody
	public ThamQuan find(@RequestBody String[] code) {
		return DAO_ThamQuan.findById(Integer.parseInt(code[0]), Integer.parseInt(code[1]), Integer.parseInt(code[2]));
	}
	
	@PostMapping(value = "/thamquan_create")
	@ResponseBody
	public int create(@RequestBody ThamQuan thamquan) {
		return DAO_ThamQuan.create(thamquan);
	}
	
	@PostMapping(value = "/thamquan_update")
	@ResponseBody
	public int update(@RequestBody ThamQuan[] thamquan) {
		return DAO_ThamQuan.update(thamquan[0], thamquan[1]);
	}
	
	@PostMapping(value = "/thamquan_delete")
	@ResponseBody
	public int delete(@RequestBody ThamQuan thamquan) {
		return DAO_ThamQuan.delete(thamquan);
	}
}