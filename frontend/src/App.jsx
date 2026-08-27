import { useEffect, useState } from 'react';

function App() {
  const [baggage, setBaggage] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const loadBaggage = async () => {
    try {
      setLoading(true);
      const response = await fetch('/api/baggage');
      if (!response.ok) throw new Error('Backend request failed');
      setBaggage(await response.json());
      setError('');
    } catch (err) {
      setError('Unable to load baggage. Please check the backend.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { loadBaggage(); }, []);

  return (
    <main className="page">
      <section className="hero">
        <div>
          <p className="eyebrow">AIRLINE OPERATIONS</p>
          <h1>Baggage Management</h1>
          <p className="subtitle">Track passenger baggage and flight details from one dashboard.</p>
        </div>
        <button onClick={loadBaggage}>Refresh</button>
      </section>

      <section className="card">
        <div className="card-header">
          <div>
            <h2>Baggage Records</h2>
            <span>{baggage.length} records</span>
          </div>
        </div>

        {loading && <p className="state">Loading baggage records...</p>}
        {error && <p className="state error">{error}</p>}

        {!loading && !error && (
          <div className="table-wrap">
            <table>
              <thead>
                <tr><th>ID</th><th>Passenger</th><th>Flight</th><th>Weight</th></tr>
              </thead>
              <tbody>
                {baggage.map(item => (
                  <tr key={item.id}>
                    <td>#{item.id}</td>
                    <td className="passenger">{item.passengerName}</td>
                    <td><span className="flight">{item.flightNumber}</span></td>
                    <td>{Number(item.weight).toFixed(1)} kg</td>
                  </tr>
                ))}
              </tbody>
            </table>
            {baggage.length === 0 && <p className="state">No baggage records found.</p>}
          </div>
        )}
      </section>
    </main>
  );
}

export default App;
