import Header from '../Header/Header';
import Navigate from '../Left-bar/Navigate';
import Logo from '../Left-bar/Logo';
import './App.css';

function App() {
  return (
    <div className='wrapper'>
      <section className='left-bar'>
        <Logo/>
        <Navigate/>
      </section>
      <section className='right-bar'>
        <Header/>
      </section>
    </div>

  );
}

export default App;
