/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.geode.cache.query.internal;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

import org.apache.geode.cache.query.QueryService;
import org.apache.geode.internal.serialization.DataSerializableFixedID;
import org.apache.geode.internal.serialization.DeserializationContext;
import org.apache.geode.internal.serialization.KnownVersion;
import org.apache.geode.internal.serialization.SerializationContext;

/**
 * An UNDEFINED value is the result of accessing an attribute of a null-valued attribute. If you
 * access an attribute that has an explicit value of null, then it is not undefined. For example, if
 * a query accesses the attribute address.city and address is null, then the result is undefined. If
 * the query accesses address, then the result is not undefined, it is null.
 *
 * @version $Revision: 1.1 $
 *
 */

public class Undefined implements DataSerializableFixedID, Comparable, Serializable {

  private static final long serialVersionUID = 6643107525908324141L;

  @SuppressWarnings("lgtm[java/useless-null-check]")
  // The "useless null check" alert is suppressed here as the first time the constructor is called,
  // QueryService.UNDEFINED is actually null, but subsequent times it is not
  public Undefined() {
    Support.assertState(QueryService.UNDEFINED == null, "UNDEFINED constant already instantiated");
  }

  @Override
  public String toString() {
    return "UNDEFINED";
  }

  @Override
  public int getDSFID() {
    return UNDEFINED;
  }

  @Override
  public void fromData(DataInput in,
      DeserializationContext context) throws IOException, ClassNotFoundException {
    // Serialized simply as a one-byte class id, as a class well known to
    // DataSerializer
  }

  @Override
  public void toData(DataOutput out,
      SerializationContext context) throws IOException {}

  @Override
  public int compareTo(Object o) {
    // An Undefined is equal to other Undefined and less than any other object
    if (o instanceof Undefined) {
      return 0;
    } else {
      return -1;
    }
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof Undefined);
  }

  @Override
  public int hashCode() {
    return Undefined.class.hashCode();
  }



  @Override
  public KnownVersion[] getSerializationVersions() {
    return null;
  }

}
