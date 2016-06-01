package cn.itcast.stormdemo;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UpperBolt extends BaseBasicBolt{

	
	//ҵ�����߼�
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		
		//�Ȼ�ȡ����һ��������ݹ���������,������tuple����
		String godName = tuple.getString(0);
		
		//����Ʒ��ת���ɴ�д
		String godName_upper = godName.toUpperCase();
		
		//��ת����ɵ���Ʒ�����ͳ�ȥ
		collector.emit(new Values(godName_upper));
		
	}

	
	
	//������bolt���Ҫ����ȥ��tuple���ֶ�
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("uppername"));
	}

}
