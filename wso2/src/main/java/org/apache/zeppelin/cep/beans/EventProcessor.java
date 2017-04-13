package org.apache.zeppelin.cep.beans;

import org.apache.log4j.Logger;
import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;
import org.wso2.siddhi.query.api.ExecutionPlan;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.definition.StreamDefinition;
import org.wso2.siddhi.query.compiler.SiddhiCompiler;

/**
 * Created by minudika on 13/4/17.
 */
public class EventProcessor {
    static final Logger log = Logger.getLogger(EventProcessor.class);
    public int count = 0;
    public String flag = "none";
    public void run(String[] params) throws InterruptedException {

        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "" +
                "define stream inputStream (symbol string, price string, volume string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream select symbol "+
                "insert into outputStream;");
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(inStreamDefinition + query);

        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);


                for(int cnt=0;cnt<inEvents.length;cnt++){
                    count++;
                    log.info("Event : " + count + ",currentDate : " + inEvents[cnt].getData(1));
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();
        flag = params[0];
        inputHandler.send(new Object[]{params[0], 700f, 100l});
        Thread.sleep(100);
        executionPlanRuntime.shutdown();
    }
}
