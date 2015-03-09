/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.navigation;

import org.assertj.db.api.ChangeColumnValueAssert;

/**
 * Interface that represents a assert with values from a column.
 *
 * @param <V> The class of a assert on value (an sub-class of {@link org.assertj.db.api.navigation.ValueAssert}).
 * @author Régis Pouiller
 */
public interface WithValuesFromColumn<V extends ValueAssert> {

  /**
   * Returns assertion methods on the value at the start point.
   *
   * @return An object to make assertions on the next value.
   */
  public V valueAtStartPoint();

  /**
   * Returns assertion methods on the value at the end point.
   *
   * @return An object to make assertions on the value.
   */
  public V valueAtEndPoint();
}