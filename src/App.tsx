import { useState, useEffect } from 'react';
import Navbar from './components/Navbar';
import CourseList from './components/CourseList';
import CourseDetail from './components/CourseDetail';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  const [selectedCourseId, setSelectedCourseId] = useState<number | null>(null);
  const [currentView, setCurrentView] = useState<'courses' | 'login' | 'register'>('courses');
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    setIsAuthenticated(!!token);
  }, []);

  const handleCourseClick = (courseId: number) => {
    setSelectedCourseId(courseId);
  };

  const handleBackToCourses = () => {
    setSelectedCourseId(null);
  };

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
    setCurrentView('courses');
  };

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userEmail');
    setIsAuthenticated(false);
    setCurrentView('login');
    setSelectedCourseId(null);
  };

  const handleShowLogin = () => {
    setCurrentView('login');
    setSelectedCourseId(null);
  };

  const handleShowRegister = () => {
    setCurrentView('register');
    setSelectedCourseId(null);
  };

  const handleRegisterSuccess = () => {
    setCurrentView('login');
  };

  if (currentView === 'register') {
    return <Register onRegisterSuccess={handleRegisterSuccess} onShowLogin={handleShowLogin} />;
  }

  if (currentView === 'login' || !isAuthenticated) {
    return <Login onLoginSuccess={handleLoginSuccess} onShowRegister={handleShowRegister} />;
  }

  return (
    <div className="min-h-screen bg-gray-100">
      <Navbar
        isAuthenticated={isAuthenticated}
        onLogout={handleLogout}
        onShowLogin={handleShowLogin}
      />
      {selectedCourseId ? (
        <CourseDetail courseId={selectedCourseId} onBack={handleBackToCourses} />
      ) : (
        <CourseList onCourseClick={handleCourseClick} />
      )}
    </div>
  );
}

export default App;
