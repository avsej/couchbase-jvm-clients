/*
 * Copyright (c) 2018 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.couchbase.client.core.service;

import com.couchbase.client.core.CoreContext;
import com.couchbase.client.core.endpoint.Endpoint;
import com.couchbase.client.core.endpoint.QueryEndpoint;
import com.couchbase.client.core.io.NetworkAddress;
import com.couchbase.client.core.service.strategy.RoundRobinSelectionStrategy;

public class QueryService extends PooledService {

  private final CoreContext context;
  private final NetworkAddress hostname;
  private final int port;

  public QueryService(final QueryServiceConfig config, final CoreContext context,
                      final NetworkAddress hostname, final int port) {
    super(config, new ServiceContext(context, hostname, port, ServiceType.QUERY));
    this.context = context;
    this.hostname = hostname;
    this.port = port;
  }

  @Override
  protected Endpoint createEndpoint() {
    return new QueryEndpoint(context, hostname, port);
  }

  @Override
  protected EndpointSelectionStrategy selectionStrategy() {
    return new RoundRobinSelectionStrategy();
  }

}