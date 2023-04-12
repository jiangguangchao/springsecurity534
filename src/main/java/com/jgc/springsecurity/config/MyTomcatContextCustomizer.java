package com.jgc.springsecurity.config;

import org.apache.catalina.*;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.ehcache.impl.internal.classes.commonslang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.stereotype.Component;
import sun.security.util.ArrayUtil;

import javax.management.ObjectName;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2022-04-19 11:47
 */

//@Component
public class MyTomcatContextCustomizer implements TomcatContextCustomizer {

    private static final Logger log = LoggerFactory.getLogger(MyTomcatContextCustomizer.class);

    @Override
    public void customize(Context context) {
        log.info("start myTomcatContextCustomizer...");

        if (context == null){
            log.warn("context is null");
            return;
        }

        log.info("context.class：[{}]", context.getClass().getName());


        Container parent = context.getParent();
        if (parent == null) {
            log.info("context.parent is null");
            return;
        }
        log.info("context.parent.class: [{}]", parent.getClass().getName());

        Container pp = parent.getParent();
        if (pp == null) {
            log.info("context.parent.parent is null");
            return;
        }
        log.info("context.parent.parent.class: [{}]", pp.getClass().getName());

        Server server = null;
        if (pp instanceof StandardEngine) {
            StandardEngine engine = (StandardEngine) pp;
            server = engine.getService().getServer();
        }

        if (server == null) {
            log.info("server is null");
            return;
        }

        addListenerForAll(server);


        log.info("end myTomcatContextCustomizer...");
    }

    private void addListenerForAll(Server server) {
        if (server == null) {
            log.error("server is null");
            return;
        }

        server.addLifecycleListener(event -> {
            log.info("server.envet:[{}]", event.getType() + ", " + event.getData());
        });

        Service[] services = server.findServices();
        if (ArrayUtils.isEmpty(services)) {
            log.error("services is null");
            return;
        }


        for (int i = 0; i < services.length; i++) {
            Service service = services[i];
            service.addLifecycleListener(event -> {
                log.info("service[{}].envet:[{}]",service.getName(), event.getType() + ", " + event.getData());
            });

            Engine engine = service.getContainer();
            if (engine == null) {
                log.error("engine is null");
                return;
            }

            engine.addLifecycleListener(event -> {
                log.info("engine[{}].envet:[{}]",engine.getName(), event.getType() + ", " + event.getData());
            });

            Container[] children = engine.findChildren();
            if (ArrayUtils.isEmpty(children)) {
                log.error("engine.child is null");
                return;
            }

            for (Container child : children) {
                child.addLifecycleListener(event -> {
                    log.info("host[{}].envet:[{}]",child.getName(), event.getType() + ", " + event.getData());
                });

                if (child instanceof StandardHost) {
                    StandardHost host = (StandardHost) child;
                    Container[] hostChildren = host.findChildren();
                    if (ArrayUtils.isEmpty(hostChildren)) {
                        log.info("host.child is null");
                        return;
                    }

                    for (Container hostChild : hostChildren) {
                        hostChild.addLifecycleListener(event -> {
                            log.info("host.child[{}].envet:[{}]",hostChild.getName(), event.getType() + ", " + event.getData());
                        });
                    }
                }

            }
        }

    }
}
