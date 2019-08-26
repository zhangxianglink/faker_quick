package easyexcel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelListener extends AnalysisEventListener {

	private List<Object> datas = new ArrayList<Object>();

	/**
	 * 每解析一行就会回调invoke
	 */
	@Override
	public void invoke(Object object, AnalysisContext context) {
		// TODO Auto-generated method stub
		System.out.println("当前行：" + context.getCurrentRowNum());
//	    List<String> list = (List<String>) object;
//	    System.out.println(list.get(2));
		System.out.println(object);
		datas.add(object);// 数据存储到list，供批量处理，或后续自己业务逻辑处理。
	}

	private void doSomething(Object object) {
		// 入库调用接口,自己逻辑
	}

	/**
	 * 整个excel解析完成后执行
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println(datas.size());
		datas.clear();// 解析结束销毁不用的资源

	}

}
