package main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "chainingQuery", namespace="")
@ManagedBean
@RequestScoped
public class AbstractChaining implements Serializable {

    @XmlTransient
    protected static String NL = System.getProperty("line.separator");

    @XmlTransient
    private List<String> facts;

    private Data data;
    private Trace trace;
    private Result result;

    public AbstractChaining(){

    }

    public AbstractChaining(Data data) {
        this.data = data;
        this.trace = new Trace(new LinkedList<>());
        facts = new LinkedList<>(data.getFacts());
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @XmlTransient
    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    //Used for listing facts in result string
    public String listFacts() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<facts.size(); i++)
        {
            if (i != facts.size() - 1)
            {
                result.append(facts.get(i) + ", ");
            }
            else
            {
                result.append(facts.get(i));
            }
        }
        return result.toString();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("PART 1. Data").append(NL).append(NL);
        if (result != null && result.getData() != null) {
            stringBuilder.append(result.getData()).append(NL).append(NL);
        }
        stringBuilder.append("PART 2. Trace").append(NL).append(NL);
        stringBuilder.append(trace).append(NL).append(NL);
        stringBuilder.append("PART 3. Results").append(NL).append(NL);
        stringBuilder.append(result);

        return stringBuilder.toString();
    }
}
