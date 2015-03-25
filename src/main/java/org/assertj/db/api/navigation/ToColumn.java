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

/**
 * Defines methods to navigate to a {@link org.assertj.db.type.Column}.
 * <p>The different methods return an assertion on one column {@link org.assertj.db.api.navigation.ColumnAssert}.</p>
 * <p>These methods exists when navigating from changes, from a {@link org.assertj.db.type.Table} of from a {@link org.assertj.db.type.Request}.</p>
 * <p>As shown in the diagram below, if navigating from table or request, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.ColumnAssert} from :</p>
 * <ul>
 *     <li>a table ({@link org.assertj.db.api.TableAssert})</li>
 *     <li>a request ({@link org.assertj.db.api.RequestAssert})</li>
 *     <li>a column ({@link org.assertj.db.api.AbstractColumnAssert})</li>
 *     <li>a value of a column ({@link org.assertj.db.api.AbstractColumnValueAssert})</li>
 *     <li>a row ({@link org.assertj.db.api.AbstractRowAssert})</li>
 *     <li>a value of a row ({@link org.assertj.db.api.AbstractRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/table_and_request/navigation/diagramOnNavigationWithTableOrRequest_ToColumn.png" alt="diagram with navigation to column" height="45%" width="45%" >
 * </p>
 * <p>If navigating from table or request, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a table ({@link org.assertj.db.api.TableAssert}) on a request ({@link org.assertj.db.api.RequestAssert}).<br>
 * So all the lines of code below are equivalent : they point on the column at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(table_or_request).column(1)......;                                   // Point directly on the column at index 1
 * assertThat(table_or_request).column().returnToOrigin().column()......;          // Use the returnToOrigin() method of AbstractAssertWithOrigin
 *                                                                                 // to return on the table or request and access to the next/second column of the list
 * assertThat(table_or_request).column().column()......;                           // Same as precedent but returnToOrigin() is implicit
 * assertThat(table_or_request).column().column(1)......;                          // The method with the index can be call too
 * assertThat(table_or_request).column(2).column(0).column(1)......;               // Idem
 * assertThat(table_or_request).column().value().column()......;
 * assertThat(table_or_request).column().value().column(1)......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(table_or_request).column().value().returnToOrigin().returnToOrigin().column(1)......;
 * </code>
 * </pre>
 * <p>As shown in the diagram below, if navigating from changes, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.ColumnAssert} from :</p>
 * <ul>
 *     <li>a change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToColumn.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>If navigating from changes, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a change ({@link org.assertj.db.api.ChangeAssert}).<br>
 * So all the lines of code below are equivalent : they point on the column at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().column(1)......;                                   // Point directly on the column at index 1
 * // Use the returnToOrigin() method of AbstractAssertWithOrigin to return on the change and access to the next/second column of the list
 * assertThat(changes).change().column().returnToOrigin().column()......;
 * assertThat(changes).change().column().column()......;                           // Same as precedent but returnToOrigin() is implicit
 * assertThat(changes).change().column().column(1)......;                          // The method with the index can be call too
 * assertThat(changes).change().column(2).column(0).column(1)......;               // Idem
 * assertThat(changes).change().column().value().column()......;
 * assertThat(changes).change().column().value().column(1)......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(changes).change().column().value().returnToOrigin().returnToOrigin().column(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <C> The class of a assertion on a column (an sub-class of {@link org.assertj.db.api.navigation.ColumnAssert}).
 */
public interface ToColumn<C extends ColumnAssert> {

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Column} in the list of {@link org.assertj.db.type.Column}s.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Column}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Column} in the list of {@link org.assertj.db.type.Column}s.
   */
  public C column();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Column} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Column}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column(int index);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Column} corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
   * @throws NullPointerException If the column name in parameter is {@code null}.
   * @throws org.assertj.db.exception.AssertJDBException If there is no {@link org.assertj.db.type.Column} with this name.
   */
  public C column(String columnName);
}
