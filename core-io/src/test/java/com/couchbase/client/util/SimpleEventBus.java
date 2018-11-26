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

package com.couchbase.client.util;

import com.couchbase.client.core.cnc.Event;
import com.couchbase.client.core.cnc.EventBus;
import com.couchbase.client.core.cnc.EventSubscription;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This super simple event bus should be used in testing only to assert certain
 * events got pushed through.
 */
public class SimpleEventBus implements EventBus {

  /**
   * Holds all published events until cleared.
   */
  private List<Event> publishedEvents = new ArrayList<>();

  /**
   * If system event should be ignored, since they add noise and nondeterminism
   * to the event bus in assertions.
   */
  private final boolean ignoreSystemEvents;

  /**
   * Creates a new {@link SimpleEventBus}.
   *
   * <p>Note that in general you want to ignore system events since they add nondeterminism during
   * assertions when things like garbage collections happen. Of course if you need to test/verify
   * system events, set the argument to false.</p>
   *
   * @param ignoreSystemEvents true if they should be ignored (recommended), false otherwise.
   */
  public SimpleEventBus(final boolean ignoreSystemEvents) {
    this.ignoreSystemEvents = ignoreSystemEvents;
  }

  @Override
  public synchronized PublishResult publish(final Event event) {
    if (!ignoreSystemEvents || event.category() != Event.Category.SYSTEM) {
      publishedEvents.add(event);
    }
    return PublishResult.SUCCESS;
  }

  @Override
  public synchronized EventSubscription subscribe(final Consumer<Event> consumer) {
    return null;
  }

  @Override
  public synchronized void unsubscribe(final EventSubscription subscription) {

  }

  public synchronized List<Event> publishedEvents() {
    return publishedEvents;
  }

}