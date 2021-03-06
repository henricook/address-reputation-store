/*
 * Copyright 2016 HM Revenue & Customs
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

package uk.gov.hmrc.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

trait JacksonMapper extends ObjectMapper {
  configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
  registerModule(DefaultScalaModule)
  setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
}

object JacksonMapper extends JacksonMapper

// Primary useful for diagnostics
object PrettyMapper extends JacksonMapper {
  configure(SerializationFeature.INDENT_OUTPUT, true)
}

