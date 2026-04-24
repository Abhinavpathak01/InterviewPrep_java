import { useState } from "react";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [questions, setQuestions] = useState([]);

  const token = localStorage.getItem("token");

  // 🔐 LOGIN
  const handleLogin = async () => {
    try {
      const res = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
      });

      const data = await res.json();

      if (data.status) {
        localStorage.setItem("token", data.token);
        setMessage("Login successful ✅");
        window.location.reload(); // refresh UI
      } else {
        setMessage("Login failed ❌");
      }
    } catch {
      setMessage("Error connecting to server ❌");
    }
  };

  // 📥 LOAD ALL QUESTIONS
  const fetchQuestions = async () => {
    try {
      const res = await fetch("http://localhost:8080/questions", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });

      const data = await res.json();
      setQuestions(data);
    } catch {
      setMessage("Failed to load questions ❌");
    }
  };

  // ⭐ ADD TO FAVORITES
  const addToFavorite = async (id) => {
    try {
      const res = await fetch(`http://localhost:8080/questions/${id}/favorite`, {
        method: "POST",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });

      if (res.ok) {
        alert("Added to favorites ✅");
      } else {
        alert("Failed ❌");
      }
    } catch (err) {
      console.log(err);
      alert("Error ❌");
    }
  };

  // ⭐ VIEW FAVORITES
  const fetchFavorites = async () => {
    try {
      const res = await fetch("http://localhost:8080/questions/favorites", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });

      const data = await res.json();
      setQuestions(data);
    } catch (err) {
      console.log(err);
      alert("Failed to load favorites ❌");
    }
  };

  // 🚪 LOGOUT
  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.reload();
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Interview Prep App 🚀</h1>

      {!token ? (
        <>
          <h2>Login</h2>

          <input
            type="email"
            placeholder="Enter email"
            onChange={(e) => setEmail(e.target.value)}
          /><br /><br />

          <input
            type="password"
            placeholder="Enter password"
            onChange={(e) => setPassword(e.target.value)}
          /><br /><br />

          <button onClick={handleLogin}>Login</button>
          <p>{message}</p>
        </>
      ) : (
        <>
          <h2>Dashboard</h2>

          <button onClick={fetchQuestions}>📥 Load Questions</button>
          <button onClick={fetchFavorites} style={{ marginLeft: "10px" }}>
            ⭐ View Favorites
          </button>
          <button onClick={handleLogout} style={{ marginLeft: "10px" }}>
            🚪 Logout
          </button>

          <br /><br />

          <ul style={{ listStyle: "none", padding: 0 }}>
            {questions.map((q) => (
              <li key={q.id} style={{ marginBottom: "15px" }}>
                <b>{q.title}</b> - {q.difficulty}
                <br />
                <button onClick={() => addToFavorite(q.id)}>
                  ⭐ Add to Favorites
                </button>
              </li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
}

export default App;