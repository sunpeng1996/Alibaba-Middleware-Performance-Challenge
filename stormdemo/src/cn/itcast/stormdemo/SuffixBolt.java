package cn.itcast.stormdemo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class SuffixBolt extends BaseBasicBolt{
	
	FileWriter fileWriter = null;
	
	
	//��bolt������й�����ֻ�ᱻ����һ��
	@Override
	public void prepare(Map stormConf, TopologyContext context) {

		try {
			fileWriter = new FileWriter("/home/hadoop/stormoutput/"+UUID.randomUUID());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	//��bolt����ĺ��Ĵ����߼�
	//ÿ�յ�һ��tuple��Ϣ���ͻᱻ����һ��
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {

		//���õ���һ��������͹�������Ʒ����
		String upper_name = tuple.getString(0);
		String suffix_name = upper_name + "_itisok";
		
		
		//Ϊ��һ��������͹�������Ʒ������Ӻ�׺
		
		try {
			fileWriter.write(suffix_name);
			fileWriter.write("\n");
			fileWriter.flush();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

	
	
	
	//��bolt�Ѿ�����Ҫ����tuple��Ϣ����һ����������Բ���Ҫ������tuple���ֶ�
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {

		
	}

}
