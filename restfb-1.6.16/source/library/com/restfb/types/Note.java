/*
 * Copyright (c) 2010-2014 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.restfb.Facebook;
import lombok.Getter;

/**
 * Represents the <a href="http://developers.facebook.com/docs/reference/api/note">Note Graph API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.5
 */
public class Note extends FacebookType {

  /**
   * The ID of the user who posted the note.
   * 
   * @return The ID of the user who posted the note.
   */
  @Getter
  @Facebook
  private NamedFacebookType from;

  /**
   * The title of the note.
   * 
   * @return The title of the note.
   */
  @Getter
  @Facebook
  private String subject;

  /**
   * The note content, an HTML string.
   * 
   * @return The note content, an HTML string.
   */
  @Getter
  @Facebook
  private String message;

  /**
   * The note icon.
   * 
   * @return The note icon.
   */
  @Getter
  @Facebook
  private String icon;

  @Facebook("created_time")
  private String createdTime;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook("comments")
  // It's possible for Facebook to return {"count":0} instead of a list of comments for whatever reason.
  // So we have this throwaway field to hold that result. There is no need to expose this through the public API; it's
  // here to let the JSON mapper work without erroring out.
  private String commentsAsObject;

  @Facebook("comments")
  private List<Comment> comments = new ArrayList<Comment>();

  private static final long serialVersionUID = 1L;

  /**
   * The time the note was initially published.
   * 
   * @return The time the note was initially published.
   */
  public Date getCreatedTime() {
    return toDateFromLongFormat(createdTime);
  }

  /**
   * The time the note was last updated.
   * 
   * @return The time the note was last updated.
   */
  public Date getUpdatedTime() {
    return toDateFromLongFormat(updatedTime);
  }

  /**
   * Comments made on the note.
   * 
   * @return Comments made on the note.
   * @since 1.6.10
   */
  public List<Comment> getComments() {
    return unmodifiableList(comments);
  }
}