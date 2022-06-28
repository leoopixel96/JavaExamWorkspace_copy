package rs;

import java.io.IOException;
import java.io.Reader;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rs.model.StimaRischio;
import rs.persistence.BadFileFormatException;
import rs.persistence.StimaRischioReader;

public class StimaRischioReaderMock implements StimaRischioReader {

	@Override
	public Map<String, Collection<StimaRischio>> readFrom(Reader reader) throws IOException, BadFileFormatException {
		HashMap<String, Collection<StimaRischio>> result = new HashMap<>();
		
		ArrayList<StimaRischio> stime = new ArrayList<>();
		stime.add(new StimaRischio("Bologna", Month.MARCH, 2017, 20));
		stime.add(new StimaRischio("Bologna", Month.JULY, 2017, 30));
		stime.add(new StimaRischio("Bologna", Month.OCTOBER, 2017, 40));
		stime.add(new StimaRischio("Bologna", Month.MARCH, 2016, 10));
		stime.add(new StimaRischio("Bologna", Month.JULY, 2016, 10));
		stime.add(new StimaRischio("Bologna", Month.OCTOBER, 2016, 10));
		result.put("Bologna", stime);
		
		stime = new ArrayList<>();
		stime.add(new StimaRischio("Ferrara", Month.APRIL, 2017, 30));
		stime.add(new StimaRischio("Ferrara", Month.AUGUST, 2017, 40));
		stime.add(new StimaRischio("Ferrara", Month.NOVEMBER, 2017, 50));
		stime.add(new StimaRischio("Ferrara", Month.APRIL, 2015, 10));
		stime.add(new StimaRischio("Ferrara", Month.AUGUST, 2015, 20));
		stime.add(new StimaRischio("Ferrara", Month.NOVEMBER, 2015, 30));
		result.put("Ferrara", stime);
		
		return result;
	}

}
