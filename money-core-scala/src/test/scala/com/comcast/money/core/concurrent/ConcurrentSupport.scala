/*
 * Copyright 2012-2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.money.core.concurrent

import java.util.concurrent.Callable

import com.comcast.money.api.SpanId
import com.comcast.money.core.internal.{ SpanContext, SpanThreadLocal }

trait ConcurrentSupport {

  val testCallable: Callable[Option[SpanId]] = new Callable[Option[SpanId]] with SpanAware {
    override def call(): Option[SpanId] = captureCurrentSpan()
  }

  val testRunnable: Runnable = new Runnable with SpanAware {
    override def run(): Unit = captureCurrentSpan()
  }
}

trait SpanAware {
  private var savedSpanId: Option[SpanId] = _

  def spanId: Option[SpanId] = savedSpanId

  def captureCurrentSpan(): Option[SpanId] = {
    savedSpanId = SpanThreadLocal.current.map(_.info.id)
    savedSpanId
  }
}
