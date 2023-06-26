import { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';
import TodoInput from './components/TodoInput'
import TodoList from './components/TodoList'

function App() {

  const [todo, setInputValue] = useState('');
  const [todos, setTodos] = useState([]);


  useEffect(() => {
    try {
      axios.get('/todo')
        .then((response) => {
          setTodos(response.data);
          debugger;
        });
    } catch (error) {
      console.error("Istek hatasi", error);
    }
  })

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const addNewTodo = async () => {
    try {
      axios.post('/todo', {
        content: todo
      })
        .then((response) => {
          console.log(response);
        });
    } catch (error) {
      console.error("Istek hatasi", error);
    }
  }

  const listItems = todos.map(todo =>
    <li className="list-group-item">{todo.content} - {todo.id} - {todo.status}</li>
  );


  return (
    <div className="App">
      <div className="container">
        <div className="row">
          <div className="col">
            <h2>TodoInput</h2>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">@</span>
              <input type="text" className="form-control" placeholder="New Todo" value={todo} onChange={handleInputChange} />
            </div>
            <div className="d-grid gap-2">
              <button onClick={addNewTodo} className="btn btn-primary" type="button">Add New Task</button>
            </div>
            <h2>TodoList</h2>
            <ul className="list-group">
              {listItems}
            </ul>
            <div className='gap-2'>
              <button type="button" class="btn btn-primary btn-lg btn-block">Block level button</button>
              <button type="button" class="btn btn-primary btn-lg btn-block">Block level button</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;