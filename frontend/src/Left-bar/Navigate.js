import './Navigate.css';

function Navigate () {
    return (
        <nav className="left-bar__navigation">
            <ul className="left-bar__navigation-list">
                <li className="left-bar__navigation-list-item">
                    <img src="../../img/main.svg" alt="Главная"/>
                    <a href="">Dashboard</a>
                </li>
                <li className="left-bar__navigation-list-item">
                    <img src="../../img/left-bar/pay.svg" alt=""/>
                    <a href="">Payment</a>
                </li>
                <li className="left-bar__navigation-list-item">
                    <img src="../../img/left-bar/settings.svg" alt=""/>
                    <a href="">Setting</a> 
                </li>
                <li className="left-bar__navigation-list-item log-out-img">
                    <img src="../../img/left-bar/logout.svg" alt=""/>
                    <a href="">Logout</a> 
                </li>
            </ul>
        </nav>
    );
}


export default Navigate;
