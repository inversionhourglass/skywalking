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
 *
 */

package org.apache.skywalking.oap.server.core.query.type;

import io.vavr.collection.Stream;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class IntValues {
    @Getter
    private List<KVInt> values = new ArrayList<>();

    public void addKVInt(KVInt e) {
        values.add(e);
    }

    public long findValue(String id, int defaultValue) {
        for (KVInt value : values) {
            if (value.getId().equals(id)) {
                return value.getValue();
            }
        }
        return defaultValue;
    }

    public long latestValue(int defaultValue) {
        return Stream.ofAll(values).map(KVInt::getValue).findLast(v -> v != defaultValue).getOrElse((long) defaultValue);
    }
}
