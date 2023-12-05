package com.zjl.archetype.web.infra.util

import com.zjl.archetype.web.infra.dao.CustomerDO
import com.zjl.archetype.web.infra.util.JsonUtil
import org.junit.Rule

/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.rules.TestName
import spock.lang.Specification

class JsonUtilSpec extends Specification {


    def "test parseJsonFromClassPathFile"() {
        def customerDO = JsonUtil.parseJsonFromClassPathFile("test/util/test.json", CustomerDO.class);
        expect:
        "zjlId" == customerDO.getCustomerId()
    }
}