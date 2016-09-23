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

package uk.co.hmrc.address.osgb

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.mappings.FieldType.{DateType, StringType}

object ESSchema {

  def defaultIndexSettings = {
    val is = new IndexSettings()
    is.shards = 1
    is.replicas = 0
    is.refreshInterval = "60s"
    is
  }

  def createIndexDefinition(indexName: String,
                            addressDocType: String = "address",
                            metadataDocType: String = "metadata",
                            settings: IndexSettings = defaultIndexSettings) = {

    val definition = CreateIndexDefinition(indexName)
    definition._settings.settings ++= settings.settings
    definition mappings {
      mapping(addressDocType) fields(
        //TODO lines should be an array - perhaps?
        field("line1") typed StringType fields(
          field("raw") typed StringType index NotAnalyzed,
          field("lines") typed StringType
          ),
        field("line2") typed StringType fields(
          field("raw") typed StringType index NotAnalyzed,
          field("lines") typed StringType
          ),
        field("line3") typed StringType fields(
          field("raw") typed StringType index NotAnalyzed,
          field("lines") typed StringType
          ),
        field("town") typed StringType fields (
          field("raw") typed StringType index NotAnalyzed
          ),
        field("postcode") typed StringType fields (
          field("raw") typed StringType index NotAnalyzed
          ),
        field("subdivision") typed StringType fields (
          field("raw") typed StringType index NotAnalyzed
          ),
        field("country") typed StringType fields (
          field("raw") typed StringType index NotAnalyzed
          )
        )
      mapping(metadataDocType) fields (
        field("completedAt") typed DateType
        )
    }
  }
}
