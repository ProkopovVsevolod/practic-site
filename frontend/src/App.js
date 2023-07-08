import Header from './Header/Header';
import Navigate from './Left-bar/Navigate';
import Logo from './Left-bar/Logo';
import {BrowserRouter, Route, Routes} from  "react-router-dom";
import './App.css';
import Register from './registration/Registration';


function App() {
  return (
    <div className='wr'>
      <Logo/>
      <Navigate/>
      <Register/>
    </div>

    // <BrowserRouter>
    //   <div className='wrapper'>
    //     <section className='left-bar'>
    //       <Logo/>
    //       <Navigate/>
    //     </section>
    //     <section className='right-bar'>
    //       <Header/>
    //         <Routes>
    //           {/* <Route path='./components'><Register/></Route> */}
    //           <Route path='./components' component = {Register}/>
    //         </Routes>
    //     </section>
    //   </div>
    // </BrowserRouter>  
  );
}

export default App;

