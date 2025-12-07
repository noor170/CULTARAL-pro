import { BookOpen, GraduationCap, LogOut, User } from 'lucide-react';
import { useState, useEffect } from 'react';

export default function Navbar({ isAuthenticated, onLogout, onShowLogin }) {
  const [userEmail, setUserEmail] = useState('');

  useEffect(() => {
    if (isAuthenticated) {
      const email = localStorage.getItem('userEmail');
      setUserEmail(email || '');
    }
  }, [isAuthenticated]);

  return (
    <nav className="bg-gradient-to-r from-emerald-600 to-teal-600 text-white shadow-lg">
      <div className="container mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-3">
            <GraduationCap className="w-8 h-8" />
            <div>
              <h1 className="text-2xl font-bold">বাংলা শিখুন</h1>
              <p className="text-sm text-emerald-100">Learn Bangla Online</p>
            </div>
          </div>
          <div className="flex items-center space-x-4">
            {isAuthenticated ? (
              <>
                <button className="flex items-center space-x-2 hover:bg-white/10 px-4 py-2 rounded-lg transition">
                  <BookOpen className="w-5 h-5" />
                  <span className="hidden md:inline">My Courses</span>
                </button>
                <div className="hidden sm:flex items-center space-x-2 bg-white/10 px-4 py-2 rounded-lg">
                  <User className="w-4 h-4" />
                  <span className="text-sm">{userEmail}</span>
                </div>
                <button
                  onClick={onLogout}
                  className="flex items-center space-x-2 hover:bg-white/10 px-4 py-2 rounded-lg transition"
                >
                  <LogOut className="w-5 h-5" />
                  <span className="hidden md:inline">Logout</span>
                </button>
              </>
            ) : (
              <button
                onClick={onShowLogin}
                className="flex items-center space-x-2 bg-white text-teal-600 hover:bg-emerald-50 px-6 py-2 rounded-lg transition font-semibold"
              >
                <User className="w-5 h-5" />
                <span>Login</span>
              </button>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}
