/*
 * Copyright 2015 HM Revenue & Customs
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

package uk.gov.hmrc.domain

import org.scalatest.{Matchers, WordSpec}

class AtedUtrSpec extends WordSpec with Matchers {

  "validation" should {

    "pass with valid format and check digit" in {
      AtedUtr.isValid("XN1200000100001") should equal(true)
      AtedUtr.isValid("xn1200000100001") should equal(true)
    }

    "fail with an empty string" in {
      AtedUtr.isValid("") should equal(false)
    }

    "fail when char at 1 is not an X" in {
      AtedUtr.isValid("AN1200000100001") should equal(false)
      AtedUtr.isValid("AN1200000100001") should equal(false)
      AtedUtr.isValid("1N1200000100001") should equal(false)
    }

    "fail char at 2 is not alpha" in {
      AtedUtr.isValid("X11200000100001") should equal(false)
    }

    "fail when chars at 3 and 4 are not digits" in {
      AtedUtr.isValid("XNX200000100001") should equal(false)
      AtedUtr.isValid("XN1X00000100001") should equal(false)
    }

    "fail when chars 5 to 9 are not 0's" in {
      AtedUtr.isValid("XN1210000100001") should equal(false)
      AtedUtr.isValid("XN1201000100001") should equal(false)
      AtedUtr.isValid("XN1200100100001") should equal(false)
      AtedUtr.isValid("XN1200010100001") should equal(false)
      AtedUtr.isValid("XN1200001100001") should equal(false)
      AtedUtr.isValid("XN12A0000100001") should equal(false)
      AtedUtr.isValid("XN120A000100001") should equal(false)
      AtedUtr.isValid("XN1200A00100001") should equal(false)
      AtedUtr.isValid("XN12A00A0100001") should equal(false)
      AtedUtr.isValid("XN12A000A100001") should equal(false)
    }

    "fail when chars 10 to 15 are not digits" in {
      AtedUtr.isValid("XN1200000A00001") should equal(false)
      AtedUtr.isValid("XN12000001A0001") should equal(false)
      AtedUtr.isValid("XN120000010A001") should equal(false)
      AtedUtr.isValid("XN1200000100A01") should equal(false)
      AtedUtr.isValid("XN12000001000A1") should equal(false)
      AtedUtr.isValid("XN120000010000A") should equal(false)
    }

    "fail when the length is wrong too short" in {
      AtedUtr.isValid("XN120000010000") should equal(false)
      AtedUtr.isValid("XN12000001000011") should equal(false)
    }

    "fail when the check character at pos 2 is incorrect" in {
      AtedUtr.isValid("XB1200000100001") should equal(false)
    }

  }

}
