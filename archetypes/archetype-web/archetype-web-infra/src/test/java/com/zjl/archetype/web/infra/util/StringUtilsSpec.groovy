package com.zjl.archetype.web.infra.util


import spock.lang.Specification

class StringUtilsSpec extends Specification {

    def "test subStringByByteLength"() {
        given:
        def str = "xxxx阿的说法233"

        when:
        def result = StringUtils.subStringByByteLength(str, charset, len);

        then:
        result == expectResult

        where:
        charset | len || expectResult
        "GBK"   | 40  || "xxxx阿的说法233"
        "GBK"   | 10  || "xxxx阿的说"
        "UTF-8" | 10  || "xxxx阿的"
        "GBK"   | 3   || "xxx"
    }


}
