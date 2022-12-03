import React, { Component } from "react";

class RightComponent extends Component {
  render() {
    return (
      <div>
        <form action="">
          <label htmlFor="">Title &rarr;</label>
          <input type="text" name="excerpt_title" id="excerpt_title" />
          <label htmlFor="">Description(Optional) &rarr;</label>
          <input type="text" name="excerpt_descp" id="excerpt_descp" />
          <label htmlFor="">Title &rarr;</label>
          <textarea name="excerpt_data" id="excerpt_data"></textarea>
        </form>
        <h3>Title &rarr;</h3>
      </div>
    );
  }
}

export default RightComponent;
