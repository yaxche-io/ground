/**
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

package edu.berkeley.ground.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.berkeley.ground.api.versions.GroundType;
import edu.berkeley.ground.api.versions.Version;

import java.util.*;

public class StructureVersion extends Version {
    // the id of the Structure containing this Version
    private String structureId;

    // the map of attribute names to types
    private Map<String, GroundType> attributes;

    @JsonCreator
    protected StructureVersion(@JsonProperty("id") String id,
                               @JsonProperty("structureId") String structureId,
                               @JsonProperty("attributes") Map<String, GroundType> attributes) {
        super(id);

        this.structureId = structureId;
        this.attributes = attributes;
    }

    @JsonProperty
    public String getStructureId() {
        return this.structureId;
    }

    @JsonProperty
    public Map<String, GroundType> getAttributes() {
        return this.attributes;
    }

}
