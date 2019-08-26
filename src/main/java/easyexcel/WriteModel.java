package easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel{

	@ExcelProperty(value = "名字", index = 0)
	private String name;
	@ExcelProperty(value = "年龄", index = 1)
	private Integer age;
	@ExcelProperty(value = "性别", index = 2)
	private String gendr;
}
