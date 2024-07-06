// pages/page.js
import React from 'react';
import SimpleNavbar from '@components/SimpleNavbar';
import CreateExchange from '@components/CreateExchange';

export default function NewPost() {
  return (
    <div className="flex flex-col min-h-screen bg-slate-100">
      <SimpleNavbar />
      <div className="flex flex-1 justify-center p-4">
        <CreateExchange />
      </div>
    </div>
  );
}
