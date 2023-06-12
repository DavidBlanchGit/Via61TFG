import React from "react";
import "./aboutStyle.css";
import githubLogo from "./gitHub.png";

export function Aboutus() {

  // Genera una fecha aleatoria
  const generateRandomDate = () => {
    const randomDay = Math.floor(Math.random() * 28) + 1;
    const randomHour = Math.floor(Math.random() * 24);
    const randomMinute = Math.floor(Math.random() * 60);

    const months = [
      "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    ];
    const randomMonth = months[Math.floor(Math.random() * months.length)];

    const currentDate = new Date();
    currentDate.setDate(randomDay);
    currentDate.setHours(randomHour);
    currentDate.setMinutes(randomMinute);

    return `${randomMonth} ${randomDay} ${currentDate.toLocaleTimeString("en-US")}`;
  };

  const alvaro = [
    { name: "Java"},
    { name: "C++"},
    { name: "C#"},
    { name: "Kotlin"},
    { name: "Python"},
    { name: "HTML"},
    { name: "CSS"},
    { name: "XML"},
    { name: "Java FX"},
    { name: "MySQL"},
    { name: "Hibernate"},
    { name: "SASS"},
    { name: "Pascal"},
    { name: "Delphi"},
    { name: ".NET"},
    { name: "Visual Studio"},
    { name: "IntelliJ"},
    { name: "NetBeans"},
    { name: "Eclipse"},
    { name: "Visual Studio Code"},
    { name: "PyCharm"},
  ];

  const david = [
    { name: "Java"},
    { name: "Java FX"},
    { name: "Python"},
    { name: "React"},
    { name: "Node"},
    { name: "Mongo"},
    { name: "Express"},
    { name: "Postgresql"},
    { name: "SQL"},
    { name: "Hibernate"},
    { name: "Kotlin"},
    { name: "HTML"},
    { name: "CSS"},
    { name: "XML"},
    { name: "MySQL"},
    { name: "Fast API"},
    { name: "SASS"},
    { name: "NetBeans"},
    { name: "Eclipse"},
    { name: "Visual Studio Code"},
    { name: "IntelliJ"},
  ];

  const santi = [
    { name: "Java"},
    { name: "Java FX"},
    { name: "React"},
    { name: "CSS"},
    { name: "HTML"},
    { name: "SASS"},
    { name: "Mongo"},
    { name: "Express"},
    { name: "Hibernate"},
    { name: "Kotlin"},
    { name: "XML"},
    { name: "MySQL"},
    { name: "Eclipse"},
    { name: "NetBeans"},
    { name: "Visual Studio Code"},
    { name: "Android Studio"},
    { name: "Express"},
    { name: "Python"},
    { name: "IntelliJ"},
    { name: "PyCharm"},
    { name: "Brackets"},
  ];

  return (
    <>
      <div className="main">
        <p className="paginas">
          <a className="noActual" href="/">Home</a> |
          <a className="actual" href="/about-us"> About us</a> |
          <a className="noActual" href="/sing-in"> Sign in</a>
        </p>
        <div className="row">
          <div className="tarjetas">
            <div className="terminal" id="black-arch">
              <div className="terminal-window">
                <p>
                  <span className="user">ÁlvaroRomán@server</span><span className="path">~/user/alvaro$</span><span className="command">ls -l</span><br />
                </p>
                <p>
                  {alvaro.map((item, index) => (
                    <React.Fragment key={index}>
                      <span className="command">drwxrwxrwx 4096 {generateRandomDate()} <span className="dir">{item.name}</span></span>
                      <br />
                    </React.Fragment>
                  ))}
                </p>
              </div>
            </div>
            <div className="terminal" id="kali">
              <div className="terminal-window">
                <p>
                  <span className="user">DavidBlanch@server</span><span className="path">~/user/david$</span><span className="command">ls -l</span><br />
                </p>
                <p>
                  {david.map((item, index) => (
                    <React.Fragment key={index}>
                      <span className="command">drwxrwxrwx 4096 {generateRandomDate()} <span className="dir">{item.name}</span></span>
                      <br />
                    </React.Fragment>
                  ))}
                </p>
              </div>
            </div>
            <div className="terminal" id="debian">
              <div className="terminal-window">
                <p>
                  <span className="user">SantiagoCórdoba@server</span><span className="path">~/user/santi$</span><span className="command">ls -l</span><br />
                </p>
                <p>
                  {santi.map((item, index) => (
                    <React.Fragment key={index}>
                      <span className="command">drwxrwxrwx 4096 {generateRandomDate()} <span className="dir">{item.name}</span></span>
                      <br />
                    </React.Fragment>
                  ))}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
