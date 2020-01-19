package cn.jgb.cloud.eureka_client.mq.local.filter;

import java.util.Map;

/**
 * 责任链模式
 */
public abstract class FilterLink{

    private FilterStrategy strategy;
    private FilterLink next;

    FilterLink(FilterStrategy strategy) {
        this.strategy = strategy;
    };

    public FilterStrategy getStrategy() {
        return strategy;
    }

    public void setNext(FilterLink next) {
        this.next = next;
    }

    public FilterLink getNext() {
        return next;
    }

    public abstract Result handleRequest(Map<String,Object> parame,Result result);
}

/**
 * A型调用链
 */
class ALink extends FilterLink {

    ALink(FilterStrategy strategy) {
        super(strategy);
    }
    @Override
    public Result handleRequest(Map<String,Object> parame,Result result) {
        FilterStrategy strategy = getStrategy();
        System.out.println(result);
        if(strategy.filter(parame,result)) {
			return result;
        }

        if(getNext() == null) {
        	return result;
		}

        return getNext().handleRequest(parame,result);
    }
}

