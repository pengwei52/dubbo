/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.Node;

/**
 * Invoker. (API/SPI, Prototype, ThreadSafe)
 *
 * Invoker 是实体域，它是 Dubbo 的核心模型，其它模型都向它靠扰，或转换成它。
 * 它代表一个可执行体，可向它发起 invoke 调用。
 * 
 * 有3中类型的Invoker：
 * 	1.本地执行类的实现
 * 		服务端：要执行demoService.sayHello()，就通过 InjvmExporter来执行
 * 	2.远程通信的实现
 * 		客户端：要执行demoService.sayHello()，它封装了DubboInvoker来进行远程通信，发送要执行的接口给server端
 * 		服务端：采用AbstractProxyInvoker代理类执行了DemoServiceImpl.sayHello()，然后将执行结果返回给client端
 * 	3.多个远程通信执行类的Invoker聚合成集群版的Invoker
 * 		客户端：要执行demoService.sayHello()，就要通过AbstractClusterInvoker来进行负载均衡，使用DubboInvoker来进行远程通信，发送要执行的接口给server端
 * 		服务端：采用AbstractProxyInvoker代理类执行了DemoServiceImpl.sayHello()，然后将执行结果返回给client端
 *
 * @see com.alibaba.dubbo.rpc.Protocol#refer(Class, com.alibaba.dubbo.common.URL)
 * @see com.alibaba.dubbo.rpc.InvokerListener
 * @see com.alibaba.dubbo.rpc.protocol.AbstractInvoker
 */
public interface Invoker<T> extends Node {

    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    Result invoke(Invocation invocation) throws RpcException;

}