package org.ikasan.dashboard.cache;

import org.ikasan.dashboard.broadcast.FlowState;
import org.ikasan.dashboard.broadcast.FlowStateBroadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import java.util.concurrent.ConcurrentHashMap;

public class FlowStateCache implements Consumer<FlowState>
{
    private Logger logger = LoggerFactory.getLogger(FlowStateCache.class);

    private static FlowStateCache INSTANCE;

    public static FlowStateCache instance()
    {
        if(INSTANCE == null)
        {
            synchronized (FlowStateCache.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new FlowStateCache();
                }
            }
        }
        return INSTANCE;
    }

    private ConcurrentHashMap<String, FlowState> cache;

    private FlowStateCache()
    {
        cache = new ConcurrentHashMap<>();
        FlowStateBroadcaster.register(this);
    }

    public void put(FlowState flowState)
    {
        String key = flowState.getModuleName() + flowState.getFlowName();
        this.cache.put(key, flowState);

        CacheStateBroadcaster.broadcast(flowState);
    }

    public FlowState get(String key)
    {
        return this.cache.get(key);
    }

    @Override
    public void accept(FlowState flowState)
    {
        logger.info("Received state change: " + flowState);
        this.put(flowState);
    }
}
