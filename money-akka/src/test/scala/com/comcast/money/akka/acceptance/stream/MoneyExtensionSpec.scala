/*
 * Copyright 2012 Comcast Cable Communications Management, LLC
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

package com.comcast.money.akka.acceptance.stream

import akka.testkit.TestKit
import akka.actor.ActorSystem
import com.comcast.money.akka.MoneyExtension
import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class MoneyExtensionSpec(_system: ActorSystem) extends TestKit(_system) with AnyWordSpecLike with Matchers {

  def this() = this{
    val configString: String =
      """
        | money {
        |  enabled = false
        | }""".stripMargin

    ActorSystem("MoneyExtensionSpec", ConfigFactory.parseString(configString))
  }

  "A MoneyExtension" should {
    "construct a new MoneyExtension from an ActorSystem without Money config" in {
      MoneyExtension(system) shouldBe a[MoneyExtension]
    }
  }

}
