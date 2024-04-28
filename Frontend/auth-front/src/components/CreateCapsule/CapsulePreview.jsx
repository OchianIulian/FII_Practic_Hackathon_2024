import React from 'react';
import PropTypes from 'prop-types';

function CapsulePreview({ data }) {
  const getObjectURL = (file) => {
    if (file) {
      return URL.createObjectURL(file);
    }
    return null;
  };

  return (
    <div className="capsule-preview">
      {data.title && <h3>{data.title}</h3>}
      {data.description && <p>{data.description}</p>}
      {data.coverImage && (
        <img src={getObjectURL(data.coverImage)} alt="Cover Preview" />
      )}
      {/* Display other file types as needed */}
    </div>
  );
}
CapsulePreview.propTypes = {
    data: PropTypes.shape({
      title: PropTypes.string,
      description: PropTypes.string,
      dateToOpen: PropTypes.string,
      isPrivate: PropTypes.bool,
      coverImage: PropTypes.instanceOf(File), 
    })
  };

export default CapsulePreview;
