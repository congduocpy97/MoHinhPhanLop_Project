import moment from "moment";
import { Model_GiaTour } from "../Model/GiaTour";
import { search } from "./Data_Service";

export const getGiaTour = async (maTour, khoiHanh) =>{
    const searchModel = JSON.parse(JSON.stringify(Model_GiaTour.GiaTour));
    searchModel.maTour = maTour
    const data = await  search(searchModel,Model_GiaTour.table_database);
    const e = data.find( f => maTour === f.maTour && 
        khoiHanh.valueOf() >= moment(f.thoiGianBatDau).valueOf() &&
        khoiHanh.valueOf() <= moment(f.thoiGianKetThuc).valueOf()
        )
    return e !== undefined ? e.thanhTien : 0;
}
