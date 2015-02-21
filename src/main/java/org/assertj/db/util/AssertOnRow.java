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
package org.assertj.db.util;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.ValueType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.db.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldBeValueTypeOfAny.shouldBeValueTypeOfAny;
import static org.assertj.db.error.ShouldHaveColumnsSize.shouldHaveColumnsSize;
import static org.assertj.db.util.Values.areEqual;

/**
 * Utility methods related to assert on row.
 *
 * @author Régis Pouiller
 */
public class AssertOnRow {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertOnRow() {
    // Empty
  }

  /**
   * Verifies that the size of a {@link org.assertj.db.type.Row} is equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param size      The size of the row.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public static <A extends AbstractAssert> A hasSize(A assertion, WritableAssertionInfo info, int size, int expected) {
    if (size != expected) {
      throw failures.failure(info, shouldHaveColumnsSize(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values of a column are equal to values in parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Info on the object to assert.
   * @param valuesList The list of values.
   * @param expected   The expected values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the values in parameter.
   */
  public static <A extends AbstractAssert> A hasValuesEqualTo(A assertion, WritableAssertionInfo info,
                                                              List<Object> valuesList, Object... expected) {
    hasSize(assertion, info, valuesList.size(), expected.length);
    int index = 0;
    for (Object value : valuesList) {
      ValueType[] possibleTypes = ValueType.getPossibleTypesForComparison(expected[index]);
      ValueType type = ValueType.getType(value);
      if (!Arrays.asList(possibleTypes).contains(type)) {
        throw failures.failure(info, shouldBeValueTypeOfAny(index, value, type, possibleTypes));
      }
      if (!areEqual(value, expected[index])) {
        if (ValueType.getType(value) == ValueType.BYTES) {
          throw failures.failure(info, shouldBeEqual(index));
        } else {
          throw failures.failure(info, shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(value,
                                                                                                               expected[index]),
                                                     expected[index]));
        }
      }
      index++;
    }
    return assertion;
  }
}