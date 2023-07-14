import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


interface ConvertResponse {
  result: string;
  err:string;
}
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls:['app.component.css']

})
export class AppComponent {
  from: string = 'String';
  to: string = 'Base64';
  data: string = '';
  result: string = '';
  err: string = '';


  constructor(private http: HttpClient) {}
  convertData() {
    const url = `http://localhost:8080/api/${this.from}/${this.to}`;
    //check base64
    const isBase64 = /^[A-Za-z0-9+/]*={0,2}$/.test(this.data);
    // check hex
    const isHex = this.data.match(this.data);
    // check bytes
    const isByte = /^([0-9A-Fa-f]{2}\s*)+$/.test(this.data);
    if (this.from === "Base64" && !isBase64) {
      this.result = "";
     this.err = "Dữ liệu đầu vào không phải là chuỗi Base64";
    } else if (this.from === "Hex" && !isHex) {
      this.result = "";
      this.err ="Dữ liệu đầu vào không phải là chuỗi Hex";
    }else if (this.from === "Byte" && !isByte){
      this.result = "";
     this.err ="Đây ko phải là chuỗi byte hợp lệ"
    }
    else if (this.data == ""){
      this.result = "";
       this.err = "Không có dữ liệu để chuyển đổi"
    }
    else {
      this.http.post<ConvertResponse>(url, {data : this.data}).subscribe(
        res => {
          this.err = "";
          this.result = res.result;
        },
        err => {
          if (err.status === 500) {
            this.result = "";
           this.err = "Không thể chuyển đổi mã này";
          } else {
            this.result = "";
            this.err = "Đã xảy ra lỗi trong quá trình chuyển đổi";
          }
        }
      );
    }
  }
  covert(){
    const fromIn = this.from;
    const dataConvert = this.data;

    this.from = this.to;
    this.to = fromIn;
    this.data = this.result;
    this.result="";
    this.result = dataConvert;

  }

}
